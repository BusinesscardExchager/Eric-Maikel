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
        var width = frame.size.width - 10
        var x = frame.width/2 - width/2
        var height = (frame.size.height*0.8) - 10
        
        println("Breedte van de afbeelding: \(width)")
        println("Breedte van het totaal \(frame.width)")
        println("X: \(x)")
        //Imageview toevoegen aan de cell met een ronde mask
        imageView = UIImageView(frame: CGRect(x: x, y: 0, width: width, height: height))
        
        //Imageview rond maken
        imageView.layer.cornerRadius = imageView.bounds.width/2
        imageView.contentMode = UIViewContentMode.ScaleAspectFill
        imageView.clipsToBounds = true
        

        //Rand toevoegen om de ronde image heen
//        imageView.layer.borderWidth = 2.5
//        var color: UIColor = UIColor(red: 20/255, green: 181/255, blue: 31/255, alpha: 1.0)
//        imageView.layer.borderColor = color.CGColor
        
        //Imageview toevoegen aan cell
        contentView.addSubview(imageView)
        
        //Label toevoegen om naam te laten zien
        let textFrame = CGRect(x: 0, y: imageView.frame.size.height, width: frame.size.width, height: frame.size.height*0.2)
        textLabel = UILabel(frame: textFrame)
        textLabel.font = UIFont.systemFontOfSize(UIFont.smallSystemFontSize())
        textLabel.textAlignment = .Center
        textLabel.font = UIFont(name: textLabel.font.fontName, size: 20)
        contentView.addSubview(textLabel)
        
        //contentView.backgroundColor = UIColor(red: 250/255, green: 20/255, blue: 26/255, alpha: 1.0)
        var layer = contentView.layer
        
        layer.shadowColor = UIColor.blackColor().CGColor
        layer.shadowOffset = CGSize(width: 0, height: 10)
        layer.shadowOpacity = 0.4
        layer.shadowRadius = 5
    }
    
    required init(coder aDecoder : NSCoder){
        super.init(coder: aDecoder)
    }
    
    func scaleImageView(scale: CGFloat, operation: String)
    {
        var oldSize = self.imageView.frame.size
        var newWidth: CGFloat?
        var newHeigth: CGFloat?
        
        if(operation == "multiply")
        {
            newWidth = self.imageView.frame.size.width * scale
            newHeigth = self.imageView.frame.size.height * scale
        }
        else if(operation == "devide")
        {
            newWidth = self.imageView.frame.size.width / scale
            newHeigth = self.imageView.frame.size.height / scale
        }
        
        self.imageView.frame.size = CGSize(width: newWidth!, height: newHeigth!)
        self.imageView.layer.cornerRadius = newWidth!/2
        imageView.frame.offset(dx: (oldSize.width - newWidth!)/2, dy: (oldSize.height - newHeigth!)/2)
        
    }
}
