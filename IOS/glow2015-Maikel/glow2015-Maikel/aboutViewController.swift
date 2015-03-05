//
//  aboutViewController.swift
//  glow2015-Maikel
//
//  Created by Fhict on 05/03/15.
//  Copyright (c) 2015 Fhict. All rights reserved.
//

import UIKit

class aboutViewController: UIViewController {

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
    @IBOutlet weak var textfield: UITextField!
    @IBAction func eggPressed(sender: AnyObject) {
    }
    
        @IBAction func showPressed(sender: AnyObject) {
            var alertview = UIAlertView(title: "The text:",
                message:textfield.text,
                delegate: nil,
                cancelButtonTitle:"Done" )
            alertview.show()
        }
    

}
