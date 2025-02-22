package br.com.motta.events.service;

import br.com.motta.events.dto.SubscriptionRankingByUser;
import br.com.motta.events.dto.SubscriptionRankingItem;
import br.com.motta.events.dto.SubscriptionResponse;
import br.com.motta.events.exception.EventNotFoundException;
import br.com.motta.events.exception.SubscriptionConflictException;
import br.com.motta.events.exception.UserIndicadorNotFoundException;
import br.com.motta.events.model.Event;
import br.com.motta.events.model.Subscription;
import br.com.motta.events.model.User;
import br.com.motta.events.repository.EventRepository;
import br.com.motta.events.repository.SubscriptionRepository;
import br.com.motta.events.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class SubscriptionService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public SubscriptionResponse createNewSubscription(String eventName, User user, Integer userId){
        // Verifica se o evento existe, se não retorna uma exception
        Event evt = eventRepository.findByPrettyName(eventName);
        if (evt == null){
            throw new EventNotFoundException("Evento " + eventName + " não existe");
        }

        //Verifica se o usuário indicador existe, se não retorna uma exception
        User indicador = null;
        if (userId != null) {
            indicador = userRepository.findById(userId).orElse(null);
            if (indicador == null){
                throw new UserIndicadorNotFoundException("Usuário " + userId + " indicador não existe");
            }
        }

        //Verifica se o usuário já está cadastrado no banco, se não, cadastra
        User userRec = userRepository.findByEmail(user.getEmail());
        if (userRec == null){
            userRec = userRepository.save(user);
        }

        //Cria a subscription
        Subscription subs = new Subscription();
        subs.setEvent(evt);
        subs.setSubscriber(userRec);
        subs.setIndication(indicador);

        //Verifica se o usuário já está cadastrado no evento, se já, retorna uma exception
        Subscription tmpSub = subscriptionRepository.findByEventAndSubscriber(evt, userRec);
        if (tmpSub != null){
            throw new SubscriptionConflictException("Já existe inscrição para o usuário " + userRec.getName() + " no evento " + evt.getTitle());
        }

        //Salva a subscription
        Subscription res = subscriptionRepository.save(subs);
        return new SubscriptionResponse(res.getSubscriptionNumber(), "https://www.codecraft.com/subscription" + res.getEvent().getPrettyName() + "/" + res.getSubscriber().getId());

    }

    public List<SubscriptionRankingItem> getCompleteRanking(String prettyName){
        Event event = eventRepository.findByPrettyName(prettyName);
        if (event == null){
            throw new EventNotFoundException("Ranking do evento " + prettyName + " não existe");
        }
        return subscriptionRepository.generateRanking(event.getEventId());
    }

    public SubscriptionRankingByUser getRankingByUser(String prettyName, Integer userId){
        List<SubscriptionRankingItem> ranking = getCompleteRanking(prettyName);
        SubscriptionRankingItem item = ranking.stream().filter(i->i.userId().equals(userId)).findFirst().orElse(null);
        if (item == null){
            throw new UserIndicadorNotFoundException("Não há inscrições com indicação do usuário " + userId);
        }
        Integer posicao = IntStream.range(0, ranking.size())
                .filter(pos -> ranking.get(pos).userId().equals(userId))
                .findFirst().getAsInt();

        return new SubscriptionRankingByUser(item, posicao + 1);
    }

}
