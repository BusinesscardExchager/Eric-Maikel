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
        var activity1 = pendingActivities(Name: "Activiteit met Maikel, Eric...", Detail: "Met Maikel, Joris...", Approved:true)
        var activity2 = pendingActivities(Name: "Activiteit met Joris, Meny...", Detail: "Met Maikel, Joris...", Approved:false)
        var activity3 = pendingActivities(Name: "Activiteit met Joris, Eric...", Detail: "Met Maikel, Joris...", Approved:false)
        var activity4 = pendingActivities(Name: "Activiteit met Maikel, Meny...", Detail: "Met Maikel, Joris...", Approved:false)
        var activity5 = pendingActivities(Name: "Activiteit met Meny, Lisa...", Detail: "Met Maikel, Joris...", Approved:false)
        var activity6 = pendingActivities(Name: "Activiteit met Lisa, Eric...", Detail: "Met Maikel, Joris...", Approved:false)
        var activity7 = pendingActivities(Name: "Activiteit met Edwin, Kees...", Detail: "Met Maikel, Joris...", Approved:true)
        var activity8 = pendingActivities(Name: "Activiteit met Sabien, Rob...", Detail: "Met Maikel, Joris...", Approved:true )
        
        self.pendingactivities += [activity1, activity2, activity3, activity4, activity5, activity6, activity7, activity8]
    }
    
    //gets all the activities
    func getActivities() -> [pendingActivities]{
        return self.pendingactivities
    }
    
    //get a activity by index
    func getActivityAtIndex(#index : Int) -> pendingActivities{
        return pendingactivities[index]
    }
    
    //deletes a activity by index
    func deleteActivityAtIndex(#index : Int)
    {
        pendingactivities.removeAtIndex(index)
    }
    
    //set the approved of a activity true
    func setApprovedTrue(#index : Int)
    {
        pendingactivities[index].approved = true
    }
    
    //gets all approved activities
    func getApprovedActivities() -> [pendingActivities]{
        var approved = [pendingActivities]()
        for var index:Int = 0 ; index < pendingactivities.count ;index += 1{
            if(pendingactivities[index].approved == true){
                approved.append(pendingactivities[index])
            }
        }
        return approved
    }
    
    //gets all unapproved activities
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