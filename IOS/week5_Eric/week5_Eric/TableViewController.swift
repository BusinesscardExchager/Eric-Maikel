    //
    //  TableViewController.swift
    //  week5_Eric
    //
    //  Created by Eric de Regter on 19-03-15.
    //  Copyright (c) 2015 Fontys. All rights reserved.
    //
    
    import UIKit
    import Alamofire
    import SwiftyJSON
    
    class TableViewController: UITableViewController {
        
        var pirates = [Pirate]()
        
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
            let cell = tableView.dequeueReusableCellWithIdentifier("Cell", forIndexPath: indexPath) as! UITableViewCell
            
            // Configure the cell...
            var currentRow = indexPath.row
            var currentPirate = self.pirates[currentRow]
            cell.textLabel?.text = currentPirate.name
            cell.detailTextLabel?.text = currentPirate.life
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
        
        /*
        // MARK: - Navigation
        
        // In a storyboard-based application, you will often want to do a little preparation before navigation
        */
        override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
            var selectedRow = self.tableView.indexPathForSelectedRow()
            var selectedPirate = self.pirates[selectedRow!.row]
            var controller = segue.destinationViewController as! DetailsViewController
            controller.selectedPirate = selectedPirate
            // Get the new view controller using [segue destinationViewController].
            // Pass the selected object to the new view controller.
        }
        
        
        func loadJsonData()
        {
            var jSONrequest = Alamofire.request(.GET, "http://athena.fhict.nl/users/i254244/service.php")
            jSONrequest.validate()
            jSONrequest.responseJSON({
                (urlREQ, urlResp, responsestring, error) -> Void in
                if error == nil
                {
                    //println(responsestring)
                    self.parseJsonData(responsestring)
                    //self.tableView.reloadData()
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
            println(jsonConverted)
            
            for (index: String, subJson: JSON) in jsonConverted{
//                let newPirate = Pirate(Name: subJson["name"].string!, Life: subJson["life"].string!, CountryOfOrigin: subJson["country_of_origin"].string!, ActiveYears: subJson["years_active"].string!, Comments: subJson["comments"].string!)
//                pirates.append(newPirate)
                
                let newActiviteit = Activiteit(Bedrijf: subJson["bedrijf"].string!, Omschrijving: subJson["omschrijving"].string!, Naam: subJson["naam"].string!, Afbeelding: subJson["afbeelding"].string!)
                
                newActiviteit.toString()
            }
        }
        
    }
