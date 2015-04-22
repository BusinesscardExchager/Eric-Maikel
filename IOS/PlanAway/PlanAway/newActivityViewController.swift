//
//  newActivityViewController.swift
//  PlanAway
//
//  Created by Eric de Regter on 26-03-15.
//  Copyright (c) 2015 Fontys. All rights reserved.
//

import UIKit

class newActivityViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func dismissKeyboard(sender: AnyObject) {
        self.resignFirstResponder()
    }
}
