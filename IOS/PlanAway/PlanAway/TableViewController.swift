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
        
        //Peoples vullen met testdata
        var person1 = Person(Name: "Meny", Image: UIImage(named: "meny.jpg")!, Email: "meny@hotmail.com")
        var person2 = Person(Name: "Lisa", Image: UIImage(named: "lisa.jpg")!, Email: "lisa@gmail.com")
        var person3 = Person(Name: "More..", Image: UIImage(named: "more")!, Email: "more")
        
        var person4 = Person(Name: "Sabien", Image: UIImage(named: "sabien")!, Email: "sabien@live.nl")
        var person5 = Person(Name: "Edwin", Image: UIImage(named: "edwin")!, Email: "edwin@gmail.com")
        var person6 = Person(Name: "Rob", Image: UIImage(named: "rob.jpg")!, Email: "rob@hotmail.com")
        people += [person1, person2, person3, person4, person5, person6]

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
        pendingactivityProvider = appDelegate.pendingactivityProvider
        approvedActivities = pendingactivityProvider?.getApprovedActivities()
        waitingActivities = pendingactivityProvider?.getUnapprovedActivities()
        NSNotificationCenter.defaultCenter().addObserver(self, selector: "loadList:",name:"load", object: nil)
        
        
    }
    
    func loadList(notification: NSNotification){
        //load data here
        println("viewWillReload")
        approvedActivities = pendingactivityProvider?.getApprovedActivities()
        waitingActivities = pendingactivityProvider?.getUnapprovedActivities()
        self.tableView.reloadData()
    }
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
        println("viewWillAppear")
        approvedActivities = pendingactivityProvider?.getApprovedActivities()
        waitingActivities = pendingactivityProvider?.getUnapprovedActivities()
        self.tableView.reloadData()
    }
    
    
    override func tableView( tableView : UITableView,  titleForHeaderInSection section: Int)->String
    {
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
        // #warning Potentially incomplete method implementation.
        // Return the number of sections.
        return 2
    }

    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete method implementation.
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
        else{
            cell.name?.text = waitingActivities?[indexPath.row].name
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
    override func tableView(tableView: UITableView, editActionsForRowAtIndexPath indexPath: NSIndexPath) -> [AnyObject]?  {
        // 1
        var shareAction = UITableViewRowAction(style: UITableViewRowActionStyle.Default, title: "Share" , handler: { (action:UITableViewRowAction!, indexPath:NSIndexPath!) -> Void in
            // 2
            let shareMenu = UIAlertController(title: nil, message: "Share using", preferredStyle: .ActionSheet)
            
            let twitterAction = UIAlertAction(title: "Twitter", style: UIAlertActionStyle.Default, handler: nil)
            let cancelAction = UIAlertAction(title: "Cancel", style: UIAlertActionStyle.Cancel, handler: nil)
            
            shareMenu.addAction(twitterAction)
            shareMenu.addAction(cancelAction)
            
            
            self.presentViewController(shareMenu, animated: true, completion: nil)
        })
        // 3
        var rateAction = UITableViewRowAction(style: UITableViewRowActionStyle.Default, title: "Rate" , handler: { (action:UITableViewRowAction!, indexPath:NSIndexPath!) -> Void in
            // 4
            let rateMenu = UIAlertController(title: nil, message: "Rate this App", preferredStyle: .ActionSheet)
            
            let appRateAction = UIAlertAction(title: "Rate", style: UIAlertActionStyle.Default, handler: nil)
            let cancelAction = UIAlertAction(title: "Cancel", style: UIAlertActionStyle.Cancel, handler: nil)
            
            rateMenu.addAction(appRateAction)
            rateMenu.addAction(cancelAction)
            
            
            self.presentViewController(rateMenu, animated: true, completion: nil)
        })
        // 5
        return [shareAction,rateAction]
    }

    
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

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using [segue destinationViewController].
        // Pass the selected object to the new view controller.
        var selectedRow = self.tableView.indexPathForSelectedRow()!.row
        
        var navController: UINavigationController = segue.destinationViewController as! UINavigationController
        
        var friendsViewController: FriendsViewController = navController.topViewController as! FriendsViewController
        friendsViewController.people = people
        friendsViewController.isWithNavController = true
    }

}
