//
//  Person.swift
//  PlanAway
//
//  Created by Eric de Regter on 13-04-15.
//  Copyright (c) 2015 Fontys. All rights reserved.
//

import UIKit

class Person: NSObject {
    var name: String?
    var image: UIImage?
    
    init(Name name: String, Image image: UIImage)
    {
        self.name = name
        self.image = image
    }
}
