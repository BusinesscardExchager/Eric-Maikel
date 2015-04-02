//
//  CollectionViewCell.swift
//  Business Cards
//
//  Created by Eric de Regter on 07-02-15.
//  Copyright (c) 2015 EDR. All rights reserved.
//

import UIKit

class CollectionViewCell: UICollectionViewCell {

    @IBOutlet var imageView: UIImageView!
    @IBOutlet var textLabel: UILabel!
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        imageView = UIImageView(frame: CGRect(x: 0, y: 0, width: frame.size.height*2/3, height: frame.size.height*2/3))
        
        imageView.layer.cornerRadius = imageView.bounds.width/2
        imageView.contentMode = UIViewContentMode.ScaleAspectFill
            
        imageView.clipsToBounds = true
        contentView.addSubview(imageView)
        
        let textFrame = CGRect(x: 0, y: imageView.frame.size.height, width: frame.size.width, height: frame.size.height/3)
        textLabel = UILabel(frame: textFrame)
        textLabel.font = UIFont.systemFontOfSize(UIFont.smallSystemFontSize())
        textLabel.textAlignment = .Center
        contentView.addSubview(textLabel)
        
    }
    
    required init(coder aDecoder : NSCoder){
        super.init(coder: aDecoder)
    }
}
