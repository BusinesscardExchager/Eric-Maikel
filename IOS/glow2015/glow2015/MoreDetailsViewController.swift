//
//  MoreDetailsViewController.swift
//  glow2015
//
//  Created by Eric de Regter on 05-03-15.
//  Copyright (c) 2015 Fontys. All rights reserved.
//

import UIKit
import MediaPlayer

class MoreDetailsViewController: UIViewController {

    
    override func viewDidLoad() {
        super.viewDidLoad()
        
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

    @IBAction func magNietButon(sender: AnyObject) {
        let alert = UIAlertView()
        alert.title = "More Details"
        alert.message = "Mag niet"
        alert.addButtonWithTitle("Close")
        alert.show()
    }
    

    
    
}
