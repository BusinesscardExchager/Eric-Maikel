//
//  GlowAct.swift
//  HellGlow World
//
//  Created by Eric de Regter on 28-02-15.
//  Copyright (c) 2015 EDR. All rights reserved.
//

import Cocoa

class GlowAct: NSObject {
    var name = ""
    var rating = 0
    var startTime = ""
    
    func showInfo()
    {
        println("The act is called \(name) and starts at \(startTime). It is given an average rating of \(rating)")
    }


}
