//
//  ViewController.m
//  SocketIO-Client
//
//  Created by Matsui Nobuyuki on 13/02/08.
//  Copyright (c) 2013年 TIS Inc. All rights reserved.
//

#import "ViewController.h"
#import "AZSocketIO.h"
#import "Commons.h"

@interface ViewController ()

@end

@implementation ViewController {
    __strong AZSocketIO *_socketIO;
    __strong UIActivityIndicatorView *_activityIndicatorView;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    _activityIndicatorView = [[UIActivityIndicatorView alloc]
                              initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleWhiteLarge];
    _activityIndicatorView.color = [UIColor blackColor];
    _activityIndicatorView.center = self.view.center;
    [self.view addSubview:_activityIndicatorView];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)viewDidUnload {
    [self setMsg:nil];
    [self setMsgArea:nil];
    [self setSend:nil];
    [super viewDidUnload];
}

- (void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];
    self.send.enabled = NO;
    [_activityIndicatorView startAnimating];
    _socketIO = [[AZSocketIO alloc] initWithHost:HOST andPort:PORT secure:NO];
    if ([PROTOCOL length] != 0) {
        _socketIO.transport = [NSMutableSet setWithObject:PROTOCOL];
    }
    _socketIO.reconnect = NO;
    __weak ViewController *blockself = self;
    [_socketIO setEventRecievedBlock:^(NSString *eventName, id data) {
        if ([eventName isEqualToString:RECV_KEY]) {
            NSString *received = [data objectAtIndex:0];
            blockself.msgArea.text = [blockself.msgArea.text stringByAppendingFormat:@"%@\n", received];
        }
    }];
    [_socketIO connectWithSuccess:^(void){
        NSLog(@"connect success %@:%@ with %@", HOST, PORT, PROTOCOL);
        dispatch_async(dispatch_get_main_queue(), ^(void){
            [_activityIndicatorView stopAnimating];
            self.send.enabled = YES;
        });
    } andFailure:^(NSError *error) {
        NSLog(@"connect failuer %@:%@ with %@ error:%@", HOST, PORT, PROTOCOL, [error description]);
        dispatch_async(dispatch_get_main_queue(), ^(void){
            [_activityIndicatorView stopAnimating];
            self.send.enabled = YES;
            [[[UIAlertView alloc] initWithTitle:@"connect failuer"
                                        message:[error description]
                                       delegate:nil
                              cancelButtonTitle:nil
                              otherButtonTitles:@"OK", nil] show];
        });
    }];
}

- (void)viewWillDisappear:(BOOL)animated
{
    [_socketIO disconnect];
    [super viewWillDisappear:animated];
}

#pragma mark IBAction

- (IBAction)onClickSend:(id)sender {
    NSString *message = self.msg.text;
    if (message != nil && [message length] != 0) {
        NSError *error = nil;
        [_socketIO emit:SEND_KEY args:message error:&error];
        if (error) {
            NSLog(@"send messge failuer msg:%@ error:%@", message, [error description]);
            [[[UIAlertView alloc] initWithTitle:@"send message failuer"
                                        message:[error description]
                                       delegate:nil
                              cancelButtonTitle:nil
                              otherButtonTitles:@"OK", nil] show];
        }
        self.msg.text = nil;
    }
}
@end
