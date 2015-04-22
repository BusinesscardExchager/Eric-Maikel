//
//  DataTableViewController.swift
//  PlanAway
//
//  Created by Fhict on 09/04/15.
//  Copyright (c) 2015 Fontys. All rights reserved.
//

import UIKit

class DataTableViewController: UITableViewController {
    
    let data = ["03-09-2015","04-09-2015","05-09-2015","06-09-2015"]
    var selectedActivity: pendingActivities?
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - Table view data source
    
    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        // Return the number of sections.
        return 1
    }
    
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // Return the number of rows in the section.
        return data.count
    }
    
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("Cell", forIndexPath: indexPath) as! UITableViewCell
        
        // Configure the cell...
        cell.textLabel?.text = data[indexPath.row]
        
        return cell
    }
    // MARK: - Navigation
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        
        //Pass the activity so it can be removed from the pendig list if it's saved
        if(segue.identifier == "dataToJsonSegue")
        {
            var navController: UINavigationController = segue.destinationViewController as! UINavigationController
            var jSONActivityTableViewController: JSONActivityTableViewController = navController.topViewController as! JSONActivityTableViewController
            jSONActivityTableViewController.pendingActivity = selectedActivity!
        }
    }
}
