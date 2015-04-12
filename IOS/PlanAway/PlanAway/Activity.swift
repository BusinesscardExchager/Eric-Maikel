//
//  Activity.swift
//  PlanAway
//
//  Created by Fhict on 26/03/15.
//  Copyright (c) 2015 Fontys. All rights reserved.
//

import UIKit

class Activity: NSObject {
    var name: String?
    var company: String?
    var detail: String?
    var image: UIImage
    var date: String?
    var place: String?
    var sitelink: String?
    var trailerlink: String?
    var aftermovie: String?
    
    init(Name name:String, Detail detail:String, Image image:String)
    {
        self.name = name
        self.detail = detail
        self.image = UIImage(named: image)!
    }
    
    init(Name name:String, Company company:String, Detail detail:String, Image image:String, Date date:String, Place place:String, SiteURL sitelink:String, TrailerURL trailerlink:String, AftermovieURL aftermovie:String)
    {
        self.name = name
        self.company = company
        self.detail = detail
        self.image = UIImage(named: image)!
        self.date = date
        self.place = place
        self.sitelink = sitelink
        self.trailerlink = trailerlink
        self.aftermovie = aftermovie
    }
    
    func toString()
    {
        println("Name: \(name) Company: \(company), Detail: \(detail), Date: \(date), Place: \(place), Site: \(sitelink), Trailer: \(trailerlink), Aftermovie: \(aftermovie)")
    }
}
