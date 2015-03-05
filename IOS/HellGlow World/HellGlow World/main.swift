//
//  main.swift
//  HellGlow World
//
//  Created by Eric de Regter on 28-02-15.
//  Copyright (c) 2015 EDR. All rights reserved.
//

import Foundation

var blueLightAct = GlowAct()
blueLightAct.name = "The Bluelight act"
blueLightAct.startTime = "22.20"
blueLightAct.rating = 8
blueLightAct.showInfo()

var redLightAct = GlowAct()
redLightAct.name = "The Redlight act"
redLightAct.startTime = "23.20"
redLightAct.rating = 9
redLightAct.showInfo()

var eindhoven = City()
eindhoven.name = "eindhoven"
eindhoven.population = 220000

eindhoven.glowActs.addObject(blueLightAct)
eindhoven.glowActs.addObject(redLightAct)

eindhoven.showInfo()