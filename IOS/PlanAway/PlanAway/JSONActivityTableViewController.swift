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

class JSONActivityTableViewController: UITableViewController, UISearchBarDelegate {
    
    @IBOutlet var searchbar: UISearchBar!
    let appDelegate = UIApplication.sharedApplication().delegate as! AppDelegate
    var activities = [Activity]()
    var searchActivities = [Activity]()
    var pendingActivity: pendingActivities?
    var searchActive = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        searchbar.delegate = self
        self.loadJsonData()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - Searchbar delegate methods
    func searchBarTextDidBeginEditing(searchBar: UISearchBar) {
        //Niet doen, anders crasht hij, zet op true bij textDidchange!
        //searchActive = true
    }
    
    func searchBarTextDidEndEditing(searchBar: UISearchBar) {
        searchActive = false
    }
    
    func searchBarCancelButtonClicked(searchBar: UISearchBar) {
        searchActive = false
        searchbar.resignFirstResponder()
    }
    
    func searchBarSearchButtonClicked(searchBar: UISearchBar) {
        searchActive = false
        searchbar.resignFirstResponder()
    }
    
    func searchBar(searchBar: UISearchBar, textDidChange searchText: String) {
        searchActive = true
        searchActivities.removeAll(keepCapacity: false)
        
        for activity in activities as [Activity]{
            if((activity.name?.lowercaseString.rangeOfString(searchText.lowercaseString)) != nil)
            {
                searchActivities.append(activity)
            }
        }
        if(searchActivities.count == 0 && searchText == "")
        {
            searchActive = false
        }
        else
        {
            searchActive = true
        }
        self.tableView.reloadData()
    }
    
    
    // MARK: - Table view data source
    
    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        // Return the number of sections.
        return 1
    }
    
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // Return the number of rows in the section.
        
        if(searchActive)
        {
            return searchActivities.count
        }
        else
        {
            return activities.count
        }
    }
    
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("Cell", forIndexPath: indexPath) as! JSONTableViewCell
        
        if(searchActive)
        {
            // Configure the cell...
            cell.lblName.text = searchActivities[indexPath.row].name
            cell.lblDetail.text = searchActivities[indexPath.row].time
            cell.cellImageView.image = searchActivities[indexPath.row].image
            cell.cellImageView!.layer.cornerRadius = cell.cellImageView!.bounds.width/2
            cell.cellImageView!.contentMode = UIViewContentMode.ScaleAspectFill
            cell.cellImageView!.clipsToBounds = true
        }
        else
        {
            // Configure the cell...
            cell.lblName.text = activities[indexPath.row].name
            cell.lblDetail.text = activities[indexPath.row].time
            cell.cellImageView.image = activities[indexPath.row].image
            cell.cellImageView!.layer.cornerRadius = cell.cellImageView!.bounds.width/2
            cell.cellImageView!.contentMode = UIViewContentMode.ScaleAspectFill
            cell.cellImageView!.clipsToBounds = true
        }
        
        return cell
    }
    
    
    // MARK: - Navigation
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        var selectedRow = self.tableView.indexPathForSelectedRow()!.row
        var cell = sender as! JSONTableViewCell
        var selectedActivity: Activity?
        
        for activity in activities
        {
            if(cell.lblName.text == activity.name)
            {
                selectedActivity = activity
            }
        }
        
        var activityDetailViewController: ActivityDetailViewController = segue.destinationViewController as! ActivityDetailViewController
        activityDetailViewController.selectedActivity = selectedActivity
        activityDetailViewController.isFromJson = true
        activityDetailViewController.pendingActivity = pendingActivity!
    }
    
    //gets the json from the webservice
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
    
    //parses the json to a activity
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
