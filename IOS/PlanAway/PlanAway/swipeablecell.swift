//
//  swipeablecell.swift
//  test
//
//  Created by Fhict on 02/04/15.
//  Copyright (c) 2015 Fontys. All rights reserved.
//

import UIKit

class swipeablecell: UITableViewCell {

    @IBOutlet var name: UILabel!
    @IBOutlet weak var addButton: UIButton!
    @IBOutlet weak var deleteButton: UIButton!
    @IBOutlet weak var pendingCell: UIView!
    
    
    @IBAction func addClick(sender: AnyObject) {
        let alert = UIAlertView()
        alert.title = "add"
        alert.message = "accept activity" + name.text!
        alert.addButtonWithTitle("Ok")
        alert.show()
        let appDelegate = UIApplication.sharedApplication().delegate as! AppDelegate
        var pendingactivityProvider = appDelegate.pendingactivityProvider as pendingActivityProvider
        var unapproved = pendingactivityProvider.getUnapprovedActivities()
        for var index:Int = 0 ; index < unapproved.count ;index += 1{
            if(unapproved[index].name == name.text){
                unapproved[index].approved = true
            }
        }
        NSNotificationCenter.defaultCenter().postNotificationName("load", object: nil)
    }
    
    @IBAction func deleteClick(sender: AnyObject) {
        let alert = UIAlertView()
        alert.title = "delete"
        alert.message = "delete activity" + name.text!
        alert.addButtonWithTitle("Ok")
        alert.show()
        
    }
    

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    override init(style: UITableViewCellStyle, reuseIdentifier: String!) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        
    }

    required init(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)    }

}
