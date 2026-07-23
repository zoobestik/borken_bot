use rand::distr::uniform::{SampleRange, SampleUniform};
use rand::{RngExt, rng};

pub mod dice;

pub fn random_range<T, R>(range: R) -> T
where
    T: SampleUniform,
    R: SampleRange<T>,
{
    rng().random_range(range)
}
