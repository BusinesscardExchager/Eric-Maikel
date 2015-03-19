//
//  DetailViewController.swift
//  json_maikel
//
//  Created by Fhict on 19/03/15.
//  Copyright (c) 2015 Fhict. All rights reserved.
//

import UIKit

class DetailViewController: UIViewController {

    @IBOutlet weak var name: UILabel!
    @IBOutlet weak var life: UILabel!
    @IBOutlet weak var active: UILabel!
    @IBOutlet weak var country: UILabel!
    @IBOutlet weak var comment: UITextView!
    var selectedPirate: Pirate?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        self.name.text = self.selectedPirate?.name
        self.life.text = self.selectedPirate?.life
        self.active.text = self.selectedPirate?.yearsActive
        self.country.text = self.selectedPirate?.countryOfOrigin
        self.comment.text = self.selectedPirate?.comments
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
