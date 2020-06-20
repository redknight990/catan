package entities;

import java.util.HashMap;

import gameplay.ResourceType;
import resources.Resource;

public class Player {
	private final HashMap<ResourceType, Integer> resourceCards = new HashMap<ResourceType, Integer>();
	private Resource color;

	public Player(Resource color) {
		clearHand();
		this.color = color;
	}

	public Player() {
		this(Resource.TEXTURE_COLOR_BLUE);
	}

	public void removeResourceCards(ResourceType resource, int count) {
		resourceCards.merge(resource, -count, Integer::sum);
	}

	public void addResourceCard(ResourceType resource, int count) {
		resourceCards.merge(resource, count, Integer::sum);
	}

	public int getResourceCards(ResourceType type) {
		return resourceCards.get(type);
	}

	public void clearHand() {
		resourceCards.put(ResourceType.BRICK, 0);
		resourceCards.put(ResourceType.SHEEP, 0);
		resourceCards.put(ResourceType.STONE, 0);
		resourceCards.put(ResourceType.FOREST, 0);
		resourceCards.put(ResourceType.WHEAT, 0);
	}

	public Resource getColor() {
		return color;
	}

	public void setColor(Resource color) {
		this.color = color;
	}
	
	public void giveCards(ResourceType type, int value) {
		resourceCards.put(type, resourceCards.get(type) + value);
	}
	
}
