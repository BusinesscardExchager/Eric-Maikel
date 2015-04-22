//
//  ContactenTableViewController.swift
//  PlanAway
//
//  Created by Fhict on 09/04/15.
//  Copyright (c) 2015 Fontys. All rights reserved.
//

import UIKit

class ContactenTableViewController: UITableViewController {
    let appDelegate = UIApplication.sharedApplication().delegate as! AppDelegate
    var people = [Person]()
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        var edwin = Person(Name: "Edwin", Image: UIImage(named: "edwin")!, Email: "edwin@gmail.com")
        var lisa = Person(Name: "Lisa", Image: UIImage(named: "lisa.jpg")!, Email: "lisa@hotmail.com")
        var meny = Person(Name: "Meny", Image: UIImage(named: "meny.jpg")!, Email: "meny@hotmail.com")
        var rob = Person(Name: "Rob", Image: UIImage(named: "rob.jpg")!, Email: "rob@hotmail.com")
        var sabien = Person(Name: "Sabien", Image: UIImage(named: "sabien")!, Email: "sabien@hotmail.com")
        var maikel = Person(Name: "Maikel", Image: UIImage(named: "maikel.jpg")!, Email: "maikel@gmail.com")
        var eric = Person(Name: "Eric", Image: UIImage(named: "eric.jpg")!, Email: "eric@gmail.com")
        var joris = Person(Name: "Joris", Image: UIImage(named: "joris.jpg")!, Email: "joris@hotmail.com")
        
        
        people = [edwin, lisa, meny, rob, sabien, maikel, eric, joris]
        
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
        return people.count
    }

    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("Cell", forIndexPath: indexPath) as! ContactTableViewCell

        // Configure the cell...
        cell.name.text = people[indexPath.row].name
        cell.email.text = people[indexPath.row].email
        cell.afbeelding.image = people[indexPath.row].image
        cell.afbeelding.layer.cornerRadius = cell.afbeelding.bounds.width/2
        cell.afbeelding.contentMode = UIViewContentMode.ScaleAspectFill
        cell.afbeelding.clipsToBounds = true
        return cell
    }
    
    override func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        let cell = tableView.cellForRowAtIndexPath(indexPath) as! ContactTableViewCell
        cell.checkbox.setOn(true, animated: true)
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
        let cells = tableView.visibleCells()
        var peopleArray = [Person]()
        for cell in cells as! [ContactTableViewCell]
        {
            if(cell.checkbox.on == true)
            {
                peopleArray.append(Person(Name: cell.name.text!, Image: cell.afbeelding.image!, Email: cell.email.text!))
            }
        }
        var peopleString = ""
        peopleString += peopleArray[0].name!
        if(peopleArray.count >= 2)
        {
            peopleString += ", " + peopleArray[1].name! + "..."
            
        }
        appDelegate.pendingactivityProvider.addActivity("Activiteit met \(peopleString)", Detail: "geen details", People: peopleArray, Approved: true)
        NSNotificationCenter.defaultCenter().postNotificationName("load", object: nil)
    }
    
    override func shouldPerformSegueWithIdentifier(identifier: String!, sender: AnyObject!) -> Bool {
        let cells = tableView.visibleCells()
        for cell in cells as! [ContactTableViewCell]
        {
            if(cell.checkbox.on == true)
            {
                return true
            }
        }
        let alert = UIAlertView()
        alert.title = "No Friends selected"
        alert.message = "Select at least 1 Friend"
        alert.addButtonWithTitle("OK")
        alert.show()
        return false
        
    }
    

}
