//
//  CollectionViewCell.swift
//  Business Cards
//
//  Created by Eric de Regter on 07-02-15.
//  Copyright (c) 2015 EDR. All rights reserved.
//

import UIKit

class CollectionViewCell: UICollectionViewCell {
    
    var imageView: UIImageView?

    @IBOutlet var timeLabel: UILabel!
    @IBOutlet var textLabel: UILabel!
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        var width = frame.size.width - 10
        var x = frame.width/2 - width/2
        var height = (frame.size.height*0.8) - 10
        
        //Imageview toevoegen aan de cell met een ronde mask
        imageView = UIImageView(frame: CGRect(x: x, y: 0, width: width, height: height))
        
        //Imageview rond maken
        imageView!.layer.cornerRadius = imageView!.bounds.width/2
        imageView!.contentMode = UIViewContentMode.ScaleAspectFill
        imageView!.clipsToBounds = true
        
        //Imageview toevoegen aan cell
        contentView.addSubview(imageView!)
        
        //Label toevoegen om naam te laten zien
        let textFrame = CGRect(x: 0, y: (imageView!.frame.size.height + 5.0), width: frame.size.width, height: frame.size.height*0.2)
        
        textLabel = UILabel(frame: textFrame)
        textLabel!.font = UIFont.systemFontOfSize(UIFont.smallSystemFontSize())
        textLabel!.textAlignment = .Center
        textLabel!.font =
            UIFont(name: textLabel!.font.fontName, size: 20)
        contentView.addSubview(textLabel!)
        
        let timeFrame = CGRect(x: 0, y: (imageView!.frame.size.height + 5.0) + 20.0, width: frame.size.width, height: frame.size.height*0.2)
        
        timeLabel = UILabel(frame: timeFrame)
        timeLabel!.font = UIFont.systemFontOfSize(UIFont.smallSystemFontSize())
        timeLabel!.textAlignment = .Center
        timeLabel!.font =
            UIFont(name: timeLabel!.font.fontName, size: 10)
        contentView.addSubview(timeLabel!)
        
        var layer = contentView.layer
        layer.shadowColor = UIColor.blackColor().CGColor
        layer.shadowOffset = CGSize(width: 0, height: 10)
        layer.shadowOpacity = 0.4
        layer.shadowRadius = 5
    }
    
    required init(coder aDecoder : NSCoder){
        super.init(coder: aDecoder)
    }
    
    //Scales the imageview
    func scaleImageView(scale: CGFloat, operation: String)
    {
        var oldSize = self.imageView!.frame.size
        var newWidth: CGFloat?
        var newHeigth: CGFloat?
        
        if(operation == "multiply")
        {
            newWidth = self.imageView!.frame.size.width * scale
            newHeigth = self.imageView!.frame.size.height * scale
        }
        else if(operation == "devide")
        {
            newWidth = self.imageView!.frame.size.width / scale
            newHeigth = self.imageView!.frame.size.height / scale
        }
        
        self.imageView!.frame.size = CGSize(width: newWidth!, height: newHeigth!)
        self.imageView!.layer.cornerRadius = newWidth!/2
        imageView!.frame.offset(dx: (oldSize.width - newWidth!)/2, dy: (oldSize.height - newHeigth!)/2)
        
    }
}
