//
//  DetailsViewController.swift
//  week5_Eric
//
//  Created by Eric de Regter on 19-03-15.
//  Copyright (c) 2015 Fontys. All rights reserved.
//

import UIKit

class DetailsViewController: UIViewController {

    
    @IBOutlet var lblName: UILabel!
    @IBOutlet var lblLife: UILabel!
    @IBOutlet var lblActiveYears: UILabel!
    @IBOutlet var lblCountryOfBirth: UILabel!
    @IBOutlet var tfComments: UITextView!
    
    var selectedPirate: Pirate?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.lblName.text = selectedPirate?.name?
        self.lblLife.text = selectedPirate?.life
        self.lblActiveYears.text = selectedPirate?.activeYears
        self.lblCountryOfBirth.text = selectedPirate?.countryOfOrigin
        self.tfComments.text = selectedPirate?.comments
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
