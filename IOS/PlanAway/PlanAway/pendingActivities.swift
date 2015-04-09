//
//  pendingActivities.swift
//  PlanAway
//
//  Created by Fhict on 09/04/15.
//  Copyright (c) 2015 Fontys. All rights reserved.
//

import UIKit

class pendingActivities: NSObject {
    var name = ""
    var detail = ""
    var approved = false
    
    init(Name name:String, Detail detail:String, Approved approved:Bool)
    {
        self.name = name
        self.detail = detail
        self.approved = approved
    }
}
