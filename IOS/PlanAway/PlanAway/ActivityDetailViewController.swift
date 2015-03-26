//
//  ActivityDetailViewController.swift
//  PlanAway
//
//  Created by Fhict on 26/03/15.
//  Copyright (c) 2015 Fontys. All rights reserved.
//

import UIKit

class ActivityDetailViewController: UIViewController {

    var selectedActivity: Activity?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = selectedActivity?.name
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
