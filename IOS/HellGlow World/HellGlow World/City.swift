//
//  City.swift
//  HellGlow World
//
//  Created by Eric de Regter on 28-02-15.
//  Copyright (c) 2015 EDR. All rights reserved.
//

import Cocoa

class City: NSObject {
    var name = ""
    var population = 0
    var glowActs:NSMutableArray = NSMutableArray()
    
    func showInfo()
    {
        println("In the city of \(name) there are currently living \(population) people. The number of acts is \(glowActs.count)")
        
        for index in 1...glowActs.count
        {
            var act = glowActs[index-1] as GlowAct
            println("The act called \(act.name) starts at: \(act.startTime)")
        }
    }
}
