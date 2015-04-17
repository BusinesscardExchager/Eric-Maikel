//
//  FriendsViewController.swift
//  PlanAway
//
//  Created by Eric de Regter on 13-04-15.
//  Copyright (c) 2015 Fontys. All rights reserved.
//

import UIKit

class FriendsViewController: UIViewController, UICollectionViewDelegateFlowLayout, UICollectionViewDataSource {
    
    var people = [Person]()
    var isWithNavController = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        people.removeAtIndex(2)
        
        let layout: UICollectionViewFlowLayout = UICollectionViewFlowLayout()
        layout.sectionInset = UIEdgeInsets(top: 10, left: 25, bottom: 0, right: 25)
        
        //Afbeelding is 2/3 van de cell, afbeelding moet vierkant zijn dus breedte:hoogte = 1:1.333
        // Er wordt 20 van de breedte afgehaald omdat er anders maar 1 cell in de breedte is ipv 2 (voor iphone 5s scherm)
        var width = ((self.view.frame.width/3) - 20) * (0.8)
        var heigth = (self.view.frame.width/3 - 20) * (1)
        layout.itemSize = CGSize(width: width, height: heigth)
        
        // Do any additional setup after loading the view.
        var collectionViewFriends = UICollectionView(frame: CGRectMake(0, 0, self.view.bounds.width, (self.view.bounds.height)), collectionViewLayout: layout)
        if(isWithNavController)
        {
            var frame = CGRectMake(0, 0, self.view.bounds.width, self.view.bounds.height - (self.tabBarController!.tabBar.frame.height) - self.navigationController!.navigationBar.frame.height - UIApplication.sharedApplication().statusBarFrame.height)
            collectionViewFriends = UICollectionView(frame: frame, collectionViewLayout: layout)
        }
        
        collectionViewFriends.dataSource = self
        collectionViewFriends.delegate = self
        collectionViewFriends.registerClass(CollectionViewCell.self, forCellWithReuseIdentifier: "Cell")
        collectionViewFriends.backgroundColor = UIColor.whiteColor()
        collectionViewFriends.scrollEnabled = false
        
        self.view.addSubview(collectionViewFriends)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func collectionView(collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return people.count
    }
    
    func collectionView(collectionView: UICollectionView, cellForItemAtIndexPath indexPath: NSIndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCellWithReuseIdentifier("Cell", forIndexPath: indexPath) as! CollectionViewCell
        var person = people[indexPath.item] as Person
        cell.textLabel!.text = person.name
        cell.textLabel!.font = UIFont(name: cell.textLabel!.font.fontName, size: 12)
        var imageView = cell.imageView
        imageView!.image = person.image
        if(isWithNavController)
        {
            if(indexPath.item <= 3)
            {
                imageView!.layer.borderColor = UIColor(red: 17/255, green: 170/255, blue: 21/255, alpha: 1).CGColor
            }
            else
            {
                imageView!.layer.borderColor = UIColor.redColor().CGColor
            }
            
            imageView!.layer.borderWidth = 1.5
        }
        
        return cell
    }
    
    func collectionView(collectionView: UICollectionView, didSelectItemAtIndexPath indexPath: NSIndexPath){
        let alert = UIAlertView()
        alert.title = "Selected Friend"
        alert.message = "You selected " + people[indexPath.row].name! + " and the email is: " + people[indexPath.row].email!
        alert.addButtonWithTitle("OK")
        alert.show()
    }
    
    
    /*
    // MARK: - Navigation
    
    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
    // Get the new view controller using segue.destinationViewController.
    // Pass the selected object to the new view controller.
    }
    */
    
}
