//
//  Activity.swift
//  PlanAway
//
//  Created by Fhict on 26/03/15.
//  Copyright (c) 2015 Fontys. All rights reserved.
//

import UIKit

class Activity: NSObject {
   var name = ""
    var detail = ""
    var image: UIImage
    
    init(Name name:String, Detail detail:String, Image image:String)
    {
        self.name = name
        self.detail = detail
        self.image = UIImage(named: image)!
    }
}
