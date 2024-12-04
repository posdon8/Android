import {
  loadFixture,
} from "@nomicfoundation/hardhat-toolbox/network-helpers";
import { expect } from "chai";
import hre from "hardhat";

describe("Counter", function () {
  async function fixture() {
    const [owner, otherAccount] = await hre.ethers.getSigners();

    const Counter = await hre.ethers.getContractFactory("Counter");
    const counter = await Counter.deploy();

    return { counter, owner, otherAccount };
  }
  describe("Deployment", function () {
    it("counter = 0", async function () {
      const { counter } = await loadFixture(fixture);

      expect(await counter.getCounter()).to.equal(0);
    });
    it("increment counter = 1", async function () {
      const { counter } = await loadFixture(fixture);

      await counter.increment();

      expect(await counter.getCounter()).to.equal(1);
    });
  });
});
