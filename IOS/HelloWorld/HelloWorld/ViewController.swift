//
//  ViewController.swift
//  HelloWorld
//
//  Created by Eric de Regter on 28-02-15.
//  Copyright (c) 2015 EDR. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func helloBtn_Click(sender: AnyObject) {
        let alert = UIAlertView()
        alert.title = "Hello World"
        alert.message = "HellGlow world"
        alert.addButtonWithTitle("Working!!")
        alert.show()
    }
    


}

