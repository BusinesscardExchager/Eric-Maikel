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
        var activity1 = Activity(Name: "Cinema", Detail: "Met Maikel, Joris...", Image: "bios.jpg")
        var activity2 = Activity(Name: "Restaurant", Detail: "Met Maikel, Joris...", Image: "food.jpg")
        var activity3 = Activity(Name: "Qlimax", Detail: "Met Maikel, Joris...", Image: "qlimax.jpg")
        var activity4 = Activity(Name: "Burgers Zoo", Detail: "Met Maikel, Joris...", Image: "burgerszoo.jpg")
        var activity5 = Activity(Name: "Efteling", Detail: "Met Maikel, Joris...", Image: "efteling.jpg")
        var activity6 = Activity(Name: "Sea Life", Detail: "Met Maikel, Joris...", Image: "sealife.jpg")
        var activity7 = Activity(Name: "Defqon 1.", Detail: "Met Maikel, Joris...", Image: "defqon.jpg")
        var activity8 = Activity(Name: "Decibel", Detail: "Met Maikel, Joris...", Image: "decibel.jpg" )

        self.activities += [activity1, activity2, activity3, activity4, activity5, activity6, activity7, activity8]
    }
    
    func getActivities() -> [Activity]{
        return self.activities
    }
    
    func getActivityAtIndex(#index : Int) -> Activity{
        return activities[index]
    }
}
