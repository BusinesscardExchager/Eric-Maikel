//
//  JSONActivityTableViewController.swift
//  PlanAway
//
//  Created by Eric de Regter on 16-04-15.
//  Copyright (c) 2015 Fontys. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON

class JSONActivityTableViewController: UITableViewController {

    var activities = [Activity]()
    
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
        return activities.count
    }

    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("Cell", forIndexPath: indexPath) as! JSONTableViewCell

        // Configure the cell...
        cell.lblName.text = activities[indexPath.row].name
        cell.lblDetail.text = activities[indexPath.row].date
        cell.cellImageView.image = activities[indexPath.row].image
        cell.cellImageView!.layer.cornerRadius = cell.cellImageView!.bounds.width/2
        cell.cellImageView!.contentMode = UIViewContentMode.ScaleAspectFill
        cell.cellImageView!.clipsToBounds = true
        
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
        // Get the new view controller using [segue destinationViewController].
        // Pass the selected object to the new view controller.
            var selectedRow = self.tableView.indexPathForSelectedRow()!.row
            var selectedActivity = activities[selectedRow]
            var activityDetailViewController: ActivityDetailViewController = segue.destinationViewController as! ActivityDetailViewController
            activityDetailViewController.selectedActivity = selectedActivity
            activityDetailViewController.isFromJson = true
    }

    
    func loadJsonData()
    {
        var jSONrequest = Alamofire.request(.GET, "http://athena.fhict.nl/users/i254244/service.php")
        jSONrequest.validate()
        jSONrequest.responseJSON(completionHandler: {
            (urlREQ, urlResp, responsestring, error) -> Void in
            if error == nil
            {
                self.parseJsonData(responsestring)
            }
            else
            {
                //Something went wrong
                println(error)
            }
        })
    }
    
    func parseJsonData(jsonData:AnyObject?)
    {
        //Create empry array for Pirates
        var jsonConverted = JSON(jsonData!)
        activities.removeAll(keepCapacity: false)
        for (index: String, subJson: JSON) in jsonConverted{
            
            var imageString = subJson["afbeelding"].string!
            let decodedData = NSData(base64EncodedData: imageString.dataUsingEncoding(NSUTF8StringEncoding, allowLossyConversion: false)!, options: NSDataBase64DecodingOptions.IgnoreUnknownCharacters)
            var decodedimage = UIImage(data: decodedData!)
            let newActiviteit = Activity(Name: subJson["naam"].string!, Company: subJson["bedrijf"].string!, Detail: subJson["omschrijving"].string!, Image: decodedimage!, Date: subJson["datum"].string!, Place: subJson["plaats"].string!, SiteURL: subJson["sitelink"].string!, TrailerURL: subJson["trailerlink"].string!, AftermovieURL: subJson["aftermovielink"].string!, Time: subJson["tijd"].string!);
            
            activities.append(newActiviteit)
        }
        self.tableView.reloadData()
    }

}
