//
//  ActivityDetailViewController.swift
//  PlanAway
//
//  Created by Eric de Regter on 02-04-15.
//  Copyright (c) 2015 Fontys. All rights reserved.
//

import UIKit

class ActivityDetailViewController: UIViewController, UICollectionViewDelegateFlowLayout, UICollectionViewDataSource {
    
    @IBOutlet var barButtonSave: UIBarButtonItem!
    @IBOutlet var lblActivityName: UILabel!
    @IBOutlet var imageViewDetail: UIImageView!
    @IBOutlet var lblCompany: UILabel!
    @IBOutlet var scrollView: UIScrollView!
    @IBOutlet var btnSite: UIButton!
    @IBOutlet var btnTrailer: UIButton!
    @IBOutlet var btnAftermovie: UIButton!
    
    var selectedActivity: Activity?
    var people = [Person]()
    var isFromJson = false
    
    let appDelegate = UIApplication.sharedApplication().delegate as! AppDelegate
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        if(!isFromJson)
        {
            self.navigationItem.rightBarButtonItem = nil
            //Peoples vullen met testdata
            var person1 = Person(Name: "Meny", Image: UIImage(named: "meny.jpg")!)
            var person2 = Person(Name: "Lisa", Image: UIImage(named: "lisa.jpg")!)
            var person3 = Person(Name: "More..", Image: UIImage(named: "more")!)
            
            var person4 = Person(Name: "Sabien", Image: UIImage(named: "sabien")!)
            var person5 = Person(Name: "Edwin", Image: UIImage(named: "edwin")!)
            var person6 = Person(Name: "Rob", Image: UIImage(named: "rob.jpg")!)
            people += [person1, person2, person3, person4, person5, person6]
        }

        
        
        // voeg labels toe
        var xPos = lblCompany.frame.origin.x
        var yPos = lblCompany.frame.origin.y
        var labelWidth = lblCompany.frame.size.width
        var labelHeigth = lblCompany.frame.size.height
        
        var xPosRight = xPos + labelWidth + 5.0
        var labelWidthRight = labelWidth + 10.0
        
        var distance: CGFloat = 25.0
        var textAlignment = NSTextAlignment.Left
        var font = UIFont(name: lblCompany.font.fontName, size: 17)
        
        lblActivityName.text = selectedActivity?.name
        lblCompany.text = "Company:"
        
        var labelPlace = UILabel(frame: CGRectMake(xPos, yPos+distance, (self.view.bounds.width - labelWidth), labelHeigth))
        labelPlace.textAlignment = textAlignment
        labelPlace.text = "Place:"
        labelPlace.font = font
        self.scrollView.addSubview(labelPlace)
        
        var labelDate = UILabel(frame: CGRectMake(xPos, yPos+(2*distance), labelWidth, labelHeigth))
        labelDate.textAlignment = textAlignment
        labelDate.text = "Date:"
        labelDate.font = font
        self.scrollView.addSubview(labelDate)
        
        var labelTime = UILabel(frame: CGRectMake(xPos, yPos+(3*distance), labelWidth, labelHeigth))
        labelTime.textAlignment = textAlignment
        labelTime.text = "Time:"
        labelTime.font = font
        self.scrollView.addSubview(labelTime)
        
        var labelDescription = UILabel(frame: CGRectMake(xPos, yPos+(5*distance), labelWidth, labelHeigth))
        labelDescription.textAlignment = textAlignment
        labelDescription.text = "Description"
        labelDescription.font = font
        self.scrollView.addSubview(labelDescription)
        
        //Labels met value
        var labelCompanyValue = UILabel(frame: CGRectMake(xPosRight, lblCompany.frame.origin.y, labelWidthRight, labelHeigth))
        labelCompanyValue.textAlignment = textAlignment
        labelCompanyValue.text = selectedActivity?.company
        labelCompanyValue.font = font
        self.scrollView.addSubview(labelCompanyValue)
        
        var labelPlaceValue = UILabel(frame: CGRectMake(xPosRight, labelPlace.frame.origin.y, labelWidthRight, labelHeigth))
        labelPlaceValue.textAlignment = textAlignment
        labelPlaceValue.text = selectedActivity?.place
        labelPlaceValue.font = font
        self.scrollView.addSubview(labelPlaceValue)
        
        var labelDateValue = UILabel(frame: CGRectMake(xPosRight, labelDate.frame.origin.y, labelWidthRight, labelHeigth))
        labelDateValue.textAlignment = textAlignment
        labelDateValue.text = selectedActivity!.date
        labelDateValue.font = font
        self.scrollView.addSubview(labelDateValue)
        
        var labelTimeValue = UILabel(frame: CGRectMake(xPosRight, labelTime.frame.origin.y, labelWidthRight, labelHeigth))
        labelTimeValue.textAlignment = textAlignment
        labelTimeValue.text = selectedActivity!.time
        labelTimeValue.font = font
        self.scrollView.addSubview(labelTimeValue)
        
        var labelDescriptionWidth = (labelDateValue.frame.origin.x + labelDateValue.bounds.size.width) - xPos
        
        var labelDescriptionValue = UILabel(frame: CGRectMake(xPos, labelDescription.frame.origin.y + 25.0, labelDescriptionWidth, labelHeigth))
        labelDescriptionValue.numberOfLines = 0
        labelDescriptionValue.textAlignment = textAlignment
        labelDescriptionValue.text = selectedActivity?.detail
        labelDescriptionValue.sizeToFit()
        labelDescriptionValue.font = font
        self.scrollView.addSubview(labelDescriptionValue)
        
        
        //Laat de links niet zien als ze leeg zijn
        if(selectedActivity?.sitelink == "")
        {
            btnSite.hidden = true
        }
        
        if(selectedActivity?.trailerlink == "")
        {
            btnTrailer.hidden = true
        }
        
        if(selectedActivity?.aftermovie == "")
        {
            btnAftermovie.hidden = true
        }
        
        //Collectionview maken
        if(!isFromJson)
        {
            let layout: UICollectionViewFlowLayout = UICollectionViewFlowLayout()
            layout.sectionInset = UIEdgeInsets(top: 10, left: 25, bottom: 0, right: 25)
            
            //Afbeelding is 2/3 van de cell, afbeelding moet vierkant zijn dus breedte:hoogte = 1:1.333
            // Er wordt 20 van de breedte afgehaald omdat er anders maar 1 cell in de breedte is ipv 2 (voor iphone 5s scherm)
            var width = ((self.view.frame.width/3) - 20) * (0.8)
            var heigth = (self.view.frame.width/3 - 20) * (1)
            layout.itemSize = CGSize(width: width, height: heigth)
            
            var yPosCollectionViewFriends = labelDescriptionValue.frame.origin.y + labelDescriptionValue.frame.size.height + distance
            var collectionViewFriends = UICollectionView(frame: CGRectMake(0, yPosCollectionViewFriends, self.view.bounds.width, (heigth + 10.0)), collectionViewLayout: layout)
            
            collectionViewFriends.dataSource = self
            collectionViewFriends.delegate = self
            collectionViewFriends.registerClass(CollectionViewCell.self, forCellWithReuseIdentifier: "Cell")
            collectionViewFriends.backgroundColor = UIColor.whiteColor()
            collectionViewFriends.scrollEnabled = false
            self.scrollView.addSubview(collectionViewFriends)
            
            
            scrollView.contentSize = CGSizeMake(self.view.bounds.width, (collectionViewFriends.frame.origin.y + collectionViewFriends.frame.size.height)-distance*2)
        }
        else
        {
                        scrollView.contentSize = CGSizeMake(self.view.bounds.width, (labelDescriptionValue.frame.origin.y + labelDescriptionValue.frame.size.height))
        }


        
        // Do any additional setup after loading the view.
        
    }
    
    func collectionView(collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 3
    }
    
    func collectionView(collectionView: UICollectionView, cellForItemAtIndexPath indexPath: NSIndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCellWithReuseIdentifier("Cell", forIndexPath: indexPath) as! CollectionViewCell
        
        var person = people[indexPath.item] as Person
        cell.textLabel!.text = person.name
        cell.textLabel!.font = UIFont(name: cell.textLabel!.font.fontName, size: 12)
        var imageView = cell.imageView
        imageView!.image = person.image
        return cell
    }
    
    func collectionView(collectionView: UICollectionView, didSelectItemAtIndexPath indexPath: NSIndexPath){
        var cell = collectionView.cellForItemAtIndexPath(indexPath) as! CollectionViewCell
        if(cell.textLabel?.text == "More..")
        {
            self.performSegueWithIdentifier("FriendsSegue", sender: self)
        }
        
    }
    
    func collectionView(collectionView: UICollectionView, didHighlightItemAtIndexPath indexPath: NSIndexPath) {
        var cell = collectionView.cellForItemAtIndexPath(indexPath) as! CollectionViewCell
        
        if(cell.textLabel?.text == "More..")
        {
            var scale: CGFloat = 0.9
            cell.scaleImageView(scale, operation: "multiply")
        }
        
    }
    
    func collectionView(collectionView: UICollectionView, didUnhighlightItemAtIndexPath indexPath: NSIndexPath) {
        var cell = collectionView.cellForItemAtIndexPath(indexPath) as! CollectionViewCell
        if(cell.textLabel?.text == "More..")
        {
            var scale: CGFloat = 0.9
            cell.scaleImageView(scale, operation: "devide")
        }
        
    }
    
    override func viewDidLayoutSubviews() {
        // Do any additional setup after loading the view
        
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func viewWillLayoutSubviews() {
        imageViewDetail.layer.cornerRadius = imageViewDetail.bounds.width/2
        imageViewDetail.contentMode = UIViewContentMode.ScaleAspectFill
        imageViewDetail.clipsToBounds = true
        
        imageViewDetail.layer.borderWidth = 2.5
        imageViewDetail.layer.borderColor = UIColor.whiteColor().CGColor
        imageViewDetail.image = selectedActivity?.image
    }
    
    @IBAction func btnSite_Click(sender: AnyObject) {
        openUrl(selectedActivity!.sitelink!)
    }
    
    @IBAction func btnTrailer_Click(sender: AnyObject) {
        openUrl(selectedActivity!.trailerlink!)
    }
    
    @IBAction func btnAftermovie_Click(sender: AnyObject) {
        openUrl(selectedActivity!.aftermovie!)
    }
    
    func openUrl(url: String)
    {
        var tempUrl = url
        if(!tempUrl.hasPrefix("http"))
        {
            tempUrl = "http://" + tempUrl
        }
        
        if let checkURL = NSURL(string: tempUrl) {
            if UIApplication.sharedApplication().openURL(checkURL) {
                //println("url sucefully opened")
            }
        }
    }
    
    // MARK: - Navigation
    
    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
        if (segue.identifier == "FriendsSegue"){
            var friendsViewController: FriendsViewController = segue.destinationViewController as! FriendsViewController
            friendsViewController.people = people
        }
        else if (segue.identifier == "saveActivity")
        {
            var controller: ViewController = segue.destinationViewController as! ViewController
            appDelegate.PlannedActivities.append(selectedActivity!)
        }
        
    }
    
    
}
