//
//  pendingActivityProvider.swift
//  PlanAway
//
//  Created by Fhict on 09/04/15.
//  Copyright (c) 2015 Fontys. All rights reserved.
//

import UIKit

class pendingActivityProvider{
    
    private var pendingactivities = [pendingActivities]()
    
    init()
    {
        var activity1 = pendingActivities(Name: "Cinema", Detail: "Met Maikel, Joris...", Approved:true)
        var activity2 = pendingActivities(Name: "Restaurant", Detail: "Met Maikel, Joris...", Approved:false)
        var activity3 = pendingActivities(Name: "Qlimax", Detail: "Met Maikel, Joris...", Approved:false)
        var activity4 = pendingActivities(Name: "Burgers Zoo", Detail: "Met Maikel, Joris...", Approved:false)
        var activity5 = pendingActivities(Name: "Efteling", Detail: "Met Maikel, Joris...", Approved:false)
        var activity6 = pendingActivities(Name: "Sea Life", Detail: "Met Maikel, Joris...", Approved:false)
        var activity7 = pendingActivities(Name: "Defqon 1.", Detail: "Met Maikel, Joris...", Approved:true)
        var activity8 = pendingActivities(Name: "Decibel", Detail: "Met Maikel, Joris...", Approved:true )
        
        self.pendingactivities += [activity1, activity2, activity3, activity4, activity5, activity6, activity7, activity8]
    }
    
    func getActivities() -> [pendingActivities]{
        return self.pendingactivities
    }
    
    func getActivityAtIndex(#index : Int) -> pendingActivities{
        return pendingactivities[index]
    }
    
    func setApprovedTrue(#index : Int)
    {
        pendingactivities[index].approved = true
    }
    
    func getApprovedActivities() -> [pendingActivities]{
        var approved = [pendingActivities]()
        for var index:Int = 0 ; index < pendingactivities.count ;index += 1{
            if(pendingactivities[index].approved == true){
                approved.append(pendingactivities[index])
            }
        }
        return approved
    }
    
    func getUnapprovedActivities() -> [pendingActivities]{
        var unapproved = [pendingActivities]()
        for var index:Int = 0 ; index < pendingactivities.count ;index += 1{
            if(pendingactivities[index].approved == false){
                unapproved.append(pendingactivities[index])
            }
        }
        return unapproved
    }
}