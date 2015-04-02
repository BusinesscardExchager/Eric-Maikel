//
//  ActivityDetailViewController.swift
//  PlanAway
//
//  Created by Eric de Regter on 02-04-15.
//  Copyright (c) 2015 Fontys. All rights reserved.
//

import UIKit

class ActivityDetailViewController: UIViewController {

    @IBOutlet var lblActivityName: UILabel!
    var selectedActivity: Activity?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        lblActivityName.text = selectedActivity?.name
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
