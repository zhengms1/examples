package com.pahlsoft.patterns.strategy;

public class MiniDucksSimulator {
	
	public static void main(String[] args) {
			Duck mallard = new MallardDuck();
			mallard.performQuack();
			mallard.performFly();
			
			// Show a dynamic runtime change to fly Behavior
			Duck model = new ModelDuck();
			model.performFly();
			model.setFlyBehavior(new FlyRocketPowered());
			model.performFly();
			
	}

}
