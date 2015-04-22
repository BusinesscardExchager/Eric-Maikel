//
//  TableViewController.swift
//  test
//
//  Created by Fhict on 02/04/15.
//  Copyright (c) 2015 Fontys. All rights reserved.
//

import UIKit


class TableViewController: UITableViewController {
    
    let appDelegate = UIApplication.sharedApplication().delegate as! AppDelegate
    var pendingactivityProvider: pendingActivityProvider?
    var approvedActivities: [pendingActivities]?
    var waitingActivities: [pendingActivities]?
    var people = [Person]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        //Fill people with test data to simulate it's from contacts
        var person1 = Person(Name: "Meny", Image: UIImage(named: "meny.jpg")!, Email: "meny@hotmail.com")
        var person2 = Person(Name: "Lisa", Image: UIImage(named: "lisa.jpg")!, Email: "lisa@gmail.com")
        var person3 = Person(Name: "More..", Image: UIImage(named: "more")!, Email: "more")
        
        var person4 = Person(Name: "Sabien", Image: UIImage(named: "sabien")!, Email: "sabien@live.nl")
        var person5 = Person(Name: "Edwin", Image: UIImage(named: "edwin")!, Email: "edwin@gmail.com")
        var person6 = Person(Name: "Rob", Image: UIImage(named: "rob.jpg")!, Email: "rob@hotmail.com")
        people += [person1, person2, person3, person4, person5, person6]
        
        //Get the pending activities
        pendingactivityProvider = appDelegate.pendingactivityProvider
        approvedActivities = pendingactivityProvider?.getApprovedActivities()
        waitingActivities = pendingactivityProvider?.getUnapprovedActivities()
        NSNotificationCenter.defaultCenter().addObserver(self, selector: "loadList:",name:"load", object: nil)
        
        //Hide the back button
        //self.navigationItem.setHidesBackButton(true, animated: true)
        
        self.tableView.reloadData()
    }
    
    //reloads the activities and the tableview from outside this class
    func loadList(notification: NSNotification){
        //load data here
        approvedActivities = pendingactivityProvider?.getApprovedActivities()
        waitingActivities = pendingactivityProvider?.getUnapprovedActivities()
        self.tableView.reloadData()
    }
    
    
    //reloads the activities and the tableview when the appear
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
        approvedActivities = pendingactivityProvider?.getApprovedActivities()
        waitingActivities = pendingactivityProvider?.getUnapprovedActivities()
        self.navigationItem.setHidesBackButton(true, animated: true)
        self.tableView.reloadData()
    }
    
    
    override func tableView( tableView : UITableView,  titleForHeaderInSection section: Int)->String
    {
        //Table section headers
        switch(section)
        {
        case 1:return "Accepted Requests"
        case 2:return ""
        default :return "Do you want to go?"
        }
        
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - Table view data source
    
    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        // Return the number of sections.
        return 2
    }
    
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // Return the number of rows in the section.
        if (section == 0){
            return waitingActivities!.count
        }
        else if (section == 1)
        {
            return approvedActivities!.count
        }
        else{
            return 0
        }
    }
    
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        // Configure the cell...
        let cell = tableView.dequeueReusableCellWithIdentifier("Cell", forIndexPath: indexPath) as! swipeablecell
        if(indexPath.section == 1)
        {
            cell.name?.text = approvedActivities?[indexPath.row].name
            cell.addButton.hidden = true
            cell.deleteButton.hidden = true
            cell.accessoryType = UITableViewCellAccessoryType.DisclosureIndicator
        }
        else
        {
            cell.name?.text = waitingActivities?[indexPath.row].name
        }
        
        return cell
    }
    
    override func shouldPerformSegueWithIdentifier(identifier: String!, sender: AnyObject!) -> Bool {
        var section = self.tableView.indexPathForSelectedRow()!.section
        if(section == 1)
        {
            return true
        }
        else
        {
            return false
        }
        
    }
    
    // MARK: - Navigation
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        //Get selectedActivity and pass it with some other information to the next viewController
        var selectedRow = self.tableView.indexPathForSelectedRow()!.row
        var selectedActivity = pendingactivityProvider!.getApprovedActivities()[selectedRow]
        
        var navController: UINavigationController = segue.destinationViewController as! UINavigationController
        
        var friendsViewController: FriendsViewController = navController.topViewController as! FriendsViewController
        friendsViewController.people = selectedActivity.people
        friendsViewController.isWithNavController = true
        friendsViewController.activity = selectedActivity
    }
    
}
