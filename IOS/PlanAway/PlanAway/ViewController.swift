//
//  ViewController.swift
//  Business Cards
//
//  Created by Eric de Regter on 07-02-15.
//  Copyright (c) 2015 EDR. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UICollectionViewDelegateFlowLayout, UICollectionViewDataSource  {
    
    var activityProvider: ActivityProvider?
    @IBOutlet var collectionView: UICollectionView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        println("View wordt geladen")
        
        //Get screen sizes
        let screenSize: CGRect = UIScreen.mainScreen().bounds
        let screenWidth = screenSize.width
        let screenHeigt = screenSize.height
        
        //Create itemProvider
        activityProvider = ActivityProvider()
        
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
        return activityProvider!.getActivities().count
    }
    
    func collectionView(collectionView: UICollectionView, cellForItemAtIndexPath indexPath: NSIndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCellWithReuseIdentifier("Cell", forIndexPath: indexPath) as! CollectionViewCell
        cell.textLabel.text = activityProvider!.getActivityAtIndex(index: indexPath.item).name
        var imageView = cell.imageView
        imageView.image = activityProvider?.getActivityAtIndex(index: indexPath.item).image
        return cell
    }
    
    func collectionView(collectionView: UICollectionView, didSelectItemAtIndexPath indexPath: NSIndexPath){
        var cell = collectionView.cellForItemAtIndexPath(indexPath) as! CollectionViewCell
        
        var activity = activityProvider?.getActivityAtIndex(index: indexPath.item)
        self.performSegueWithIdentifier("DetailSegue", sender: activity)
    }
    
    func collectionView(collectionView: UICollectionView, didHighlightItemAtIndexPath indexPath: NSIndexPath) {
        var cell = collectionView.cellForItemAtIndexPath(indexPath) as!CollectionViewCell
        
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
            var activityDetailViewController: ActivityDetailViewController = segue.destinationViewController as!ActivityDetailViewController
            var selectedActivity = sender as! Activity
            activityDetailViewController.selectedActivity = selectedActivity
        }
    }
    
}

