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
        
        //Create dummy people
        var edwin = Person(Name: "Edwin", Image: UIImage(named: "edwin")!, Email: "edwin@gmail.com")
        var lisa = Person(Name: "Lisa", Image: UIImage(named: "lisa.jpg")!, Email: "lisa@hotmail.com")
        var meny = Person(Name: "Meny", Image: UIImage(named: "meny.jpg")!, Email: "meny@hotmail.com")
        var rob = Person(Name: "Rob", Image: UIImage(named: "rob.jpg")!, Email: "rob@hotmail.com")
        var sabien = Person(Name: "Sabien", Image: UIImage(named: "sabien")!, Email: "sabien@hotmail.com")
        var maikel = Person(Name: "Maikel", Image: UIImage(named: "maikel.jpg")!, Email: "maikel@gmail.com")
        
        
        people = [edwin, lisa, meny, rob, sabien, maikel]
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - Table view methods
    
    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        // Return the number of sections.
        return 1
    }
    
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // Return the number of rows in the section.
        return people.count
    }
    
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("Cell", forIndexPath: indexPath) as! ContactTableViewCell
        
        // Configure the cell...
        cell.selectionStyle = UITableViewCellSelectionStyle.None
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
        
        if(cell.checkbox.on)
        {
            cell.checkbox.setOn(false, animated: true)
        }
        else
        {
            cell.checkbox.setOn(true, animated: true)
        }
        
    }
    
    override func tableView(tableView: UITableView, didDeselectRowAtIndexPath indexPath: NSIndexPath) {
        
    }
    
    
    // MARK: - Navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        
        //Get the selected people and add them to the created activity
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
