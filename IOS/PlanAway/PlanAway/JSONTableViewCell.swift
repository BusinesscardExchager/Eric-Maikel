//
//  JSONTableViewCell.swift
//  PlanAway
//
//  Created by Eric de Regter on 16-04-15.
//  Copyright (c) 2015 Fontys. All rights reserved.
//

import UIKit

class JSONTableViewCell: UITableViewCell {

    
    @IBOutlet var cellImageView: UIImageView!
    @IBOutlet var lblName: UILabel!
    @IBOutlet var lblDetail: UILabel!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}