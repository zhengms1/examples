package com.pahlsoft.patterns.strategy;


public class MuteQuack implements QuackBehavior {

	public void quack() {
		System.out.println("<<Silence>>");
	}

}
