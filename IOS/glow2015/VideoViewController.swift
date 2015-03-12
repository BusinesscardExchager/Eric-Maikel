//
//  VideoViewController.swift
//  glow2015
//
//  Created by Eric de Regter on 05-03-15.
//  Copyright (c) 2015 Fontys. All rights reserved.
//

import UIKit
import MediaPlayer

class VideoViewController: UIViewController {

    var moviePlayer : MPMoviePlayerController?
    override func viewDidLoad() {
        super.viewDidLoad()
        playVideo()
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */
    @IBAction func closeView(sender: AnyObject) {
        stopPlayingVideo()
        self.dismissViewControllerAnimated(true, completion: nil)
    }
    
    func playVideo() {
        
        let mainBundle = NSBundle.mainBundle()
        
        //let url = mainBundle.URLForResource("video", withExtension: "mp4")
        let path = mainBundle.pathForResource("video", ofType: "mp4")
        let url = NSURL.fileURLWithPath(path!)
        if let player = moviePlayer{
            stopPlayingVideo()
        }
        
        moviePlayer = MPMoviePlayerController(contentURL: url)
        
        if let player = moviePlayer{
            NSNotificationCenter.defaultCenter().addObserver(self, selector: "videoHasFinishedPlaying:", name: MPMoviePlayerPlaybackDidFinishNotification, object: nil)

            println("Successfully instantiated the movie player")
            var videoSize = CGRectMake(0, 50, self.view.bounds.width, 200)
            
            player.view.frame = videoSize
                view.addSubview(player.view)
            player.prepareToPlay()
        }
        else
        {
            println("Failed to instantiate the movieplayer")
        }
        
    }
    
    func stopPlayingVideo(){
        if let player = moviePlayer{
            NSNotificationCenter.defaultCenter().removeObserver(self)
            player.pause()
            //player.view.removeFromSuperview()
        }
    }
    
    func videoHasFinishedPlaying(notification: NSNotification){
        println("Video finished playing")
        
        let reason = notification.userInfo![MPMoviePlayerPlaybackDidFinishReasonUserInfoKey] as NSNumber?
        
        if let theReason = reason{
            let reasonValue = MPMovieFinishReason(rawValue: theReason.integerValue)
            
            switch reasonValue!{
            case .PlaybackEnded:
                println("Playback ended")
                
            case .PlaybackError:
                println("Error happened")
                
            default:
                println("Another event happened")
            }
            println("Finish Reason = \(theReason)")
            stopPlayingVideo()
        }
        
    }
}
