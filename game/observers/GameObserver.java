package observers;

import entities.Player;
import gameplay.ResourceType;
import scripts.GameManager;

import java.util.ArrayList;

public class GameObserver {

    public enum DiceEvents {
        DICE_ROLLED
    }

    public enum PlayerEvent {
        PLAYER_REMOVED,
        PLAYER_ADDED,
        PLAYER_COLOR_CHANGED, PLAYER_TURN,
    }

    public enum PlayerHandEvent {
        RESOURCES_ADDED,
        RESOURCES_REMOVED
    }

    ArrayList<PlayerEventSubject> playerSubscribers = new ArrayList<>();
    ArrayList<DiceEventSubject> diceSubjects = new ArrayList<>();
    ArrayList<PlayerHandEventSubject> playerHandEventSubjects = new ArrayList<>();

    /**
     * Broadcast a player game event to any subscribers
     * @param event Event Type being broadcasted
     * @param context Data to be passed to subjects
     */
    public void broadcast(PlayerEvent event, Player context)
    {
        for (PlayerEventSubject subject: playerSubscribers) {
            subject.onPlayerEvent(event, context);
        }
    }

    /**
     * Broadcast a dice game event to any subscribers
     * @param event Event Type being broadcasted
     * @param context Data to be passed to subjects
     */
    public void broadcast(DiceEvents event, int context)
    {
        for (DiceEventSubject subject: diceSubjects) {
            subject.onDiceEvent(event, context);
        }
    }

    public void broadcast(PlayerHandEvent event, ResourceType type, int count) {
        for(PlayerHandEventSubject subject: playerHandEventSubjects) {
            subject.onPlayerHandEvent(event, type, count);
        }
    }

    public int register(PlayerHandEventSubject subject) {
        playerHandEventSubjects.add(subject);
        return playerHandEventSubjects.size() - 1;
    }

    /**
     * Register a subject to recieve game events
     * @param subject subject to recieve events
     * @return index of stored subjects
     */
    public int register(PlayerEventSubject subject)
    {
        playerSubscribers.add(subject);
        return playerSubscribers.size() - 1;
    }

    /**
     * Register a subject to recieve game events
     * @param subject subject to recieve events
     * @return index of stored subjects
     */
    public int register(DiceEventSubject subject)
    {
        diceSubjects.add(subject);
        return diceSubjects.size() - 1;
    }

    //TODO: remove subject from events if we really need it
}