//
//  ViewController.swift
//  Business Cards
//
//  Created by Eric de Regter on 07-02-15.
//  Copyright (c) 2015 EDR. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON

class ViewController: UIViewController, UICollectionViewDelegateFlowLayout, UICollectionViewDataSource  {
    
    var activities = [Activity]()
    var isFinished: Bool = false
    
    @IBOutlet var collectionView: UICollectionView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        println("View wordt geladen")
        
        //Create test data
        var act1 = Activity(Name: "Bioscoop", Company: "Pathé", Detail: "Pathé behoort tot één van de beste bioscopen van Eindhoven.", Image: UIImage(named: "bios.jpg")!, Date: "21-03-2015", Place: "Eindhoven", SiteURL: "https://www.pathe.nl/bioscoop/eindhoven", TrailerURL: "https://www.youtube.com/watch?v=kl8F-8tR8to", AftermovieURL: "")
        var act2 = Activity(Name: "Sealife", Company: "Sea Life", Detail: "Ontdek de verrassende onderwaterwereld in SEA LIFE Scheveningen en ontmoet onze fantastische dieren: roggen, haaien, zeesterren, piranha’s en nog veel meer.", Image: UIImage(named: "sealife.jpg")!, Date: "Altijd", Place: "Scheveningen", SiteURL: "https://www.visitsealife.com/scheveningen/", TrailerURL: "", AftermovieURL: "")
        var act3 = Activity(Name: "Burgers Zoo", Company: "Burgers Zoo", Detail: "Duik in 8 miljoen liter water, ga op avontuur in de overdekte jungle en bewonder de gieren in onze woestijn! Beleef 45 hectare dierenpark in Burgers' Zoo!", Image: UIImage(named: "burgerszoo.jpg")!, Date: "Altijd", Place: "Arnhem", SiteURL: "http://www.burgerszoo.nl", TrailerURL: "", AftermovieURL: "")
        activities += [act1, act2, act3]
        
        //Get screen sizes
        let screenSize: CGRect = UIScreen.mainScreen().bounds
        let screenWidth = screenSize.width
        let screenHeigt = screenSize.height
        
        //Create itemProvider
        
        //Set layout etc
        let layout: UICollectionViewFlowLayout = UICollectionViewFlowLayout()
        layout.sectionInset = UIEdgeInsets(top: 10, left: 25, bottom: 0, right: 25)
        
        //Afbeelding is 2/3 van de cell, afbeelding moet vierkant zijn dus breedte:hoogte = 1:1.333
        // Er wordt 20 van de breedte afgehaald omdat er anders maar 1 cell in de breedte is ipv 2
        var width = ((screenWidth/2) - 20) * (0.8)
        var heigth = (screenWidth/2 - 20) * (1)
        layout.itemSize = CGSize(width: width, height: heigth)
        
        var frame = CGRectMake(0, 0, self.view.bounds.width, self.view.bounds.height - (self.tabBarController!.tabBar.frame.height) - self.navigationController!.navigationBar.frame.height - UIApplication.sharedApplication().statusBarFrame.height)
        
        collectionView = UICollectionView(frame: frame, collectionViewLayout: layout)
        collectionView!.dataSource = self
        collectionView!.delegate = self
        collectionView!.registerClass(CollectionViewCell.self, forCellWithReuseIdentifier: "Cell")
        collectionView!.backgroundColor = UIColor.whiteColor()
        self.view.addSubview(collectionView!)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: Animation
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
        //self.loadJsonData()
        println("View will appear")
        animateCollectionView()
    }
    
    func animateCollectionView() {
        self.collectionView.layoutIfNeeded()
        let cells = self.collectionView!.visibleCells()
        let collectionHeight: CGFloat = collectionView.bounds.size.height
        for i in cells {
            let cell: CollectionViewCell = i as! CollectionViewCell
            
            //Om de richting van de animatie te veranderen: draai 0 en collectionHeight om. Of -collectionHeight
            cell.transform = CGAffineTransformMakeTranslation(0, -collectionHeight)
        }
        
        var indexCell = 0
        
        for a in cells {
            let cell: CollectionViewCell = a as! CollectionViewCell
            UIView.animateWithDuration(0.8, delay: 0.01 * Double(indexCell), usingSpringWithDamping: 1, initialSpringVelocity: 0, options: nil, animations: {
                cell.transform = CGAffineTransformMakeTranslation(0, 0)
                }, completion: nil)
            indexCell++
        }
    }
    
    // MARK: Collectionview
    
    func collectionView(collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        //        return activityProvider!.getActivities().count
        return activities.count
    }
    
    func collectionView(collectionView: UICollectionView, cellForItemAtIndexPath indexPath: NSIndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCellWithReuseIdentifier("Cell", forIndexPath: indexPath) as! CollectionViewCell
        var activity = activities[indexPath.item] as Activity
        cell.textLabel!.text = activity.name
        var imageView = cell.imageView
        imageView!.image = activity.image
        return cell
    }
    
    func collectionView(collectionView: UICollectionView, didSelectItemAtIndexPath indexPath: NSIndexPath){
        var cell = collectionView.cellForItemAtIndexPath(indexPath) as! CollectionViewCell
        
        var activity = activities[indexPath.item] as Activity
        self.performSegueWithIdentifier("DetailSegue", sender: activity)
    }
    
    func collectionView(collectionView: UICollectionView, didHighlightItemAtIndexPath indexPath: NSIndexPath) {
        var cell = collectionView.cellForItemAtIndexPath(indexPath) as! CollectionViewCell
        
        var scale: CGFloat = 0.9
        //        var newWidth = cell.imageView.bounds.width * scale
        //        var newHeight = cell.imageView.bounds.height * scale
        //        cell.imageView.frame.size = CGSize(width: newWidth, height: newHeight)
        cell.scaleImageView(scale, operation: "multiply")
    }
    
    func collectionView(collectionView: UICollectionView, didUnhighlightItemAtIndexPath indexPath: NSIndexPath) {
        var cell = collectionView.cellForItemAtIndexPath(indexPath) as! CollectionViewCell
        var scale: CGFloat = 0.9
        cell.scaleImageView(scale, operation: "devide")
    }
    
    // MARK: Segue
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if segue.identifier == "DetailSegue"
        {
            var activityDetailViewController: ActivityDetailViewController = segue.destinationViewController as! ActivityDetailViewController
            var selectedActivity = sender as! Activity
            activityDetailViewController.selectedActivity = selectedActivity
        }
    }
    
    func loadJsonData()
    {
        var jSONrequest = Alamofire.request(.GET, "http://athena.fhict.nl/users/i254244/service.php")
        jSONrequest.validate()
        jSONrequest.responseJSON(completionHandler: {
            (urlREQ, urlResp, responsestring, error) -> Void in
            if error == nil
            {
                self.parseJsonData(responsestring)
            }
            else
            {
                //Something went wrong
                println(error)
            }
        })
    }
    
    func parseJsonData(jsonData:AnyObject?)
    {
        //Create empry array for Pirates
        var jsonConverted = JSON(jsonData!)
        activities.removeAll(keepCapacity: false)
        for (index: String, subJson: JSON) in jsonConverted{
            
            var imageString = subJson["afbeelding"].string!
            let decodedData = NSData(base64EncodedData: imageString.dataUsingEncoding(NSUTF8StringEncoding, allowLossyConversion: false)!, options: NSDataBase64DecodingOptions.IgnoreUnknownCharacters)
            var decodedimage = UIImage(data: decodedData!)
            let newActiviteit = Activity(Name: subJson["naam"].string!, Company: subJson["bedrijf"].string!, Detail: subJson["omschrijving"].string!, Image: decodedimage!, Date: subJson["datum"].string!, Place: subJson["plaats"].string!, SiteURL: subJson["sitelink"].string!, TrailerURL: subJson["trailerlink"].string!, AftermovieURL: subJson["aftermovielink"].string!);
            
            activities.append(newActiviteit)
        }
        self.collectionView.reloadData()
        self.animateCollectionView()
    }
    
}

