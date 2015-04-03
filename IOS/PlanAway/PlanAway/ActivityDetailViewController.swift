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
    @IBOutlet var imageViewDetail: UIImageView!
    
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
    
    override func viewWillLayoutSubviews() {
        imageViewDetail.layer.cornerRadius = imageViewDetail.bounds.width/2
        imageViewDetail.contentMode = UIViewContentMode.ScaleAspectFill
        imageViewDetail.clipsToBounds = true
        
        imageViewDetail.layer.borderWidth = 2.5
        imageViewDetail.layer.borderColor = UIColor.whiteColor().CGColor
        imageViewDetail.image = selectedActivity?.image
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
