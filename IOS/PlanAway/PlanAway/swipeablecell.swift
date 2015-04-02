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

    
    @IBAction func addClick(sender: AnyObject) {
        let alert = UIAlertView()
        alert.title = "add"
        alert.message = "add button selected " + name.text!
        alert.addButtonWithTitle("Ok")
        alert.show()
    }
    
    @IBAction func deleteClick(sender: AnyObject) {
        let alert = UIAlertView()
        alert.title = "delete"
        alert.message = "delete button selected " + name.text!
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
