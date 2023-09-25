Feature: Test the Home Page

@ignore
Scenario: Test the broken links in All Tab

Given User lands on the "Default" home page
And Select the language "Hindi"
And Verify user is on Home > All Page
When User clicks on each Thumbnails
Then Verify links are not broken

@ignore
Scenario: Test the broken links in Cricket Tab

Given User clicks on the cricket tab
And Verify Cricket Tab is selected
When User scrolls to the 3rd page
And User clicks the links
Then Verify Cricket Links are not broken
#And switch to parent tab

Scenario: Verify WebStories

Given User lands on the "Default" home page
And Verify Web Stories Heading is Visible
And Capture Text on each Web Story
When User clicks on any web story
Then Capture text on each stories of the WebStory
And User clicks next webstory button
And Capture text on each stories of the WebStory
And User should land on home page