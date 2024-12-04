pragma solidity ^0.8.27;

contract Counter {
    uint256 counter;

    constructor() {}

    function getCounter() public view returns (uint256) {
        return counter;
    }

    function increment() public {
        counter++;
    }
}
