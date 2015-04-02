//
//  ActivityProvider.swift
//  PlanAway
//
//  Created by Eric de Regter on 02-04-15.
//  Copyright (c) 2015 Fontys. All rights reserved.
//

import UIKit

class ActivityProvider{
   
    private var activities = [Activity]()
    
    init()
    {
        var activity1 = Activity(Name: "Defqon 1.", Detail: "Met Maikel, Joris...", Image: "defqon.jpg")
        var activity2 = Activity(Name: "Decibel", Detail: "Met Maikel, Joris...", Image: "decibel.jpg" )
        var activity3 = Activity(Name: "Qlimax", Detail: "Met Maikel, Joris...", Image: "qlimax")
        var activity4 = Activity(Name: "Burgers Zoo", Detail: "Met Maikel, Joris...", Image: "burgerszoo.jpeg")
        var activity5 = Activity(Name: "Efteling", Detail: "Met Maikel, Joris...", Image: "efteling.jpg")
        var activity6 = Activity(Name: "Sea Life", Detail: "Met Maikel, Joris...", Image: "sealife.jpg")
        self.activities += [activity1, activity2, activity3, activity4, activity5, activity6]
    }
    
    func getActivities() -> [Activity]{
        return self.activities
    }
    
    func getActivityAtIndex(#index : Int) -> Activity{
        return activities[index]
    }
}
