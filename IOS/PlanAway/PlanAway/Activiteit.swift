//
//  activiteit.swift
//  week5_Eric
//
//  Created by Eric de Regter on 09-04-15.
//  Copyright (c) 2015 Fontys. All rights reserved.
//

import UIKit
import Foundation

class Activiteit: NSObject {
   
    var bedrijf: String?
    var omschrijving: String?
    var naam: String?
    var afbeelding: String?
    
    init(Bedrijf bedrijf:String, Omschrijving omschrijving:String, Naam naam:String, Afbeelding afbeelding:String)
    {
        self.bedrijf = bedrijf
        self.omschrijving = omschrijving
        self.naam = naam
        self.afbeelding = afbeelding
    }
    
    func toString()
    {
        println("Naam: \(naam), Bedrijf: \(bedrijf), Omschrijving: \(omschrijving), Afbeelding: \(afbeelding)")
    }
}
