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
        
        //Get screen sizes
        let screenSize: CGRect = UIScreen.mainScreen().bounds
        let screenWidth = screenSize.width
        let screenHeigt = screenSize.height
        let screenWidthThreeQ = screenWidth * 0.75
        let screenHeightThird = (screenHeigt * 0.25)
        
        //Create itemProvider
        activityProvider = ActivityProvider()
        
        //Set layout etc
        let layout: UICollectionViewFlowLayout = UICollectionViewFlowLayout()
        layout.sectionInset = UIEdgeInsets(top: 20, left: 10, bottom: 10, right: 10)
        layout.itemSize = CGSize(width: 90, height: 120)
        collectionView = UICollectionView(frame: self.view.frame, collectionViewLayout: layout)
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
    
    
    func collectionView(collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return activityProvider!.getActivities().count
    }
    
    func collectionView(collectionView: UICollectionView, cellForItemAtIndexPath indexPath: NSIndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCellWithReuseIdentifier("Cell", forIndexPath: indexPath) as CollectionViewCell
        cell.textLabel.text = activityProvider!.getActivityAtIndex(index: indexPath.item).name
        var imageView = cell.imageView
        imageView.image = activityProvider?.getActivityAtIndex(index: indexPath.item).image
        return cell
    }
    
    func collectionView(collectionView: UICollectionView, didSelectItemAtIndexPath indexPath: NSIndexPath){
        var activity = activityProvider?.getActivityAtIndex(index: indexPath.item)
        
        self.performSegueWithIdentifier("DetailSegue", sender: activity)
    }
//
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if segue.identifier? == "DetailSegue"
        {
            var activityDetailViewController: ActivityDetailViewController = segue.destinationViewController as ActivityDetailViewController
            var selectedActivity = sender as Activity
            activityDetailViewController.selectedActivity = selectedActivity
        }
    }
    
}

