//
//  ViewController.h
//  SocketIO-Client
//
//  Created by Matsui Nobuyuki on 13/02/08.
//  Copyright (c) 2013年 TIS Inc. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController

@property (weak, nonatomic) IBOutlet UITextField *msg;
@property (weak, nonatomic) IBOutlet UITextView *msgArea;
@property (weak, nonatomic) IBOutlet UIButton *send;
- (IBAction)onClickSend:(id)sender;

@end
