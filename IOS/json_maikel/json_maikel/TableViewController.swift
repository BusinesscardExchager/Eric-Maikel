//
//  TableViewController.swift
//  json_maikel
//
//  Created by Fhict on 19/03/15.
//  Copyright (c) 2015 Fhict. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON

class TableViewController: UITableViewController {

    var pirates = [Pirate]()
    
    
    func loadJsonData()
    {
        var jSONrequest = Alamofire.request(.GET, "http://athena.fhict.nl/users/i886625/pirates.json")
        jSONrequest.validate()
        jSONrequest.responseJSON
            { (urlREQ, urlResp, responsestring, error) -> Void in
                
                if error == nil
                {
                    println(responsestring)
                    self.parseJsonData(responsestring)
                }
                else
                {
                    //Something went wrong
                    println(error)
                    
                }
        }
    }
    
    func parseJsonData(jsonData:AnyObject?)
    {
        
        var jsonConverted = JSON(jsonData!)
        println(jsonConverted)
        
        for (index: String, subJson: JSON) in jsonConverted {
            
            let newPirate = Pirate()
            newPirate.life = subJson["life"].string
            newPirate.countryOfOrigin = subJson["country_of_origin"].string
            newPirate.yearsActive = subJson["years_active"].string
            newPirate.name = subJson["name"].string
            newPirate.comments = subJson["comments"].string
            pirates.append(newPirate)
        }
        self.tableView.reloadData()
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.loadJsonData()
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        // #warning Potentially incomplete method implementation.
        // Return the number of sections.
        return 1
    }

    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete method implementation.
        // Return the number of rows in the section.
        return pirates.count
    }

    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("Cell", forIndexPath: indexPath) as UITableViewCell
        
        if (pirates.count > 0)
        {
            var currentPirate = self.pirates[indexPath.item]
            cell.textLabel?.text = currentPirate.name
        }
        return cell
    
    }
    

    /*
    // Override to support conditional editing of the table view.
    override func tableView(tableView: UITableView, canEditRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        // Return NO if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(tableView: UITableView, commitEditingStyle editingStyle: UITableViewCellEditingStyle, forRowAtIndexPath indexPath: NSIndexPath) {
        if editingStyle == .Delete {
            // Delete the row from the data source
            tableView.deleteRowsAtIndexPaths([indexPath], withRowAnimation: .Fade)
        } else if editingStyle == .Insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(tableView: UITableView, moveRowAtIndexPath fromIndexPath: NSIndexPath, toIndexPath: NSIndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(tableView: UITableView, canMoveRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        // Return NO if you do not want the item to be re-orderable.
        return true
    }
    */

    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        var selectedRow = self.tableView.indexPathForSelectedRow()
        var selectedPirate = self.pirates[selectedRow!.row]
        var controller = segue.destinationViewController as DetailViewController
        controller.selectedPirate = selectedPirate
    }


}
