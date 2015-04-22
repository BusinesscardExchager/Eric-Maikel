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
        var group1 = [Person]()
        var group2 = [Person]()
        var group3 = [Person]()
        var group4 = [Person]()
        var group5 = [Person]()
        var group6 = [Person]()
        var group7 = [Person]()
        var group8 = [Person]()
        
        var edwin = Person(Name: "Edwin", Image: UIImage(named: "edwin")!, Email: "edwin@gmail.com")
        var lisa = Person(Name: "Lisa", Image: UIImage(named: "lisa.jpg")!, Email: "lisa@hotmail.com")
        var meny = Person(Name: "Meny", Image: UIImage(named: "meny.jpg")!, Email: "meny@hotmail.com")
        var rob = Person(Name: "Rob", Image: UIImage(named: "rob.jpg")!, Email: "rob@hotmail.com")
        var sabien = Person(Name: "Sabien", Image: UIImage(named: "sabien")!, Email: "sabien@hotmail.com")
        var maikel = Person(Name: "Maikel", Image: UIImage(named: "maikel.jpg")!, Email: "maikel@gmail.com")
        var eric = Person(Name: "Eric", Image: UIImage(named: "eric.jpg")!, Email: "eric@gmail.com")
        var joris = Person(Name: "Joris", Image: UIImage(named: "joris.jpg")!, Email: "joris@hotmail.com")
        
        group1 = [maikel, eric, joris]
        group2 = [joris, meny, maikel]
        group3 = [joris, eric]
        group4 = [maikel, meny, joris]
        group5 = [meny, lisa, eric, sabien]
        group6 = [lisa, eric, maikel]
        group7 = [edwin, sabien, joris]
        group8 = [sabien, rob, eric]
        
        
        var activity1 = pendingActivities(Name: "Activiteit met Maikel, Eric...", Detail: "Met Maikel, Joris...", Approved:true, People: group1)
        var activity2 = pendingActivities(Name: "Activiteit met Joris, Meny...", Detail: "Met Maikel, Joris...", Approved:false, People: group2)
        var activity3 = pendingActivities(Name: "Activiteit met Joris, Eric...", Detail: "Met Maikel, Joris...", Approved:false, People: group3)
        var activity4 = pendingActivities(Name: "Activiteit met Maikel, Meny...", Detail: "Met Maikel, Joris...", Approved:false, People: group4)
        var activity5 = pendingActivities(Name: "Activiteit met Meny, Lisa...", Detail: "Met Maikel, Joris...", Approved:false, People: group5)
        var activity6 = pendingActivities(Name: "Activiteit met Lisa, Eric...", Detail: "Met Maikel, Joris...", Approved:false, People: group6)
        var activity7 = pendingActivities(Name: "Activiteit met Edwin, Sabien...", Detail: "Met Maikel, Joris...", Approved:true, People: group7)
        var activity8 = pendingActivities(Name: "Activiteit met Sabien, Rob...", Detail: "Met Maikel, Joris...", Approved:true, People: group8)
        
        self.pendingactivities += [activity1, activity2, activity3, activity4, activity5, activity6, activity7, activity8]
    }
    
    //gets all the activities
    func getActivities() -> [pendingActivities]{
        return self.pendingactivities
    }
    
    //adds a activity
    func addActivity(name: String, Detail detail: String, People people: [Person], Approved approved: Bool)
    {
        var act = pendingActivities(Name: name, Detail: detail, Approved:approved, People: people)
        self.pendingactivities.append(act)
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
    
    //deletes a activity by name
    func deleteActivityByName(#name : String)
    {
        for var index:Int = 0 ; index < pendingactivities.count ;index += 1{
            if(pendingactivities[index].name == name){
                pendingactivities.removeAtIndex(index)
            }
        }
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