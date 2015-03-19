
//
//  Pirate.swift
//  week5_Eric
//
//  Created by Eric de Regter on 19-03-15.
//  Copyright (c) 2015 Fontys. All rights reserved.
//

import Foundation
class Pirate{
    
    var name:String?
    var life:String?
    var countryOfOrigin:String?
    var activeYears: String?
    var comments:String?
    
    init(Name name:String, Life life:String, CountryOfOrigin countryOfOrigin:String, ActiveYears activeYears:String, Comments comments:String)
    {
        self.name = name
        self.life = life
        self.countryOfOrigin = countryOfOrigin
        self.activeYears = activeYears
        self.comments = comments
    }
}

